package com.example.filRouge.service.memberService;

import com.example.filRouge.Repository.MemberRepository;
import com.example.filRouge.Repository.TokenRepository;
import com.example.filRouge.controller.vm.AuthenticationRequest;
import com.example.filRouge.controller.vm.AuthenticationResponse;
import com.example.filRouge.entities.*;
import com.example.filRouge.exception.AlreadyExistException;
import com.example.filRouge.exception.NotFoundException;
import com.example.filRouge.helpers.email.EmailService;
import com.example.filRouge.helpers.password.PasswordGenerator;
import com.example.filRouge.service.jwtService.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    final private MemberRepository memberRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    final private EmailService emailService;
    @Override
    public AuthenticationResponse create(Member member) throws MessagingException, IOException {
        if(memberRepository.findMemberByNum(member.getNum())==null)
        {
            String password=PasswordGenerator.generatePassword();
            member.setAccessionDate(LocalDate.now());
            member.setPassword(password);
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            member.setRole(Role.STUDENT);
            Member savedUser=memberRepository.save(member);
            var jwtToken = jwtService.generateToken(member);
            var refreshToken = jwtService.generateRefreshToken(member);
            saveUserToken(savedUser, jwtToken);
            emailService.sendEmail(member,password);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        }
        throw new AlreadyExistException();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = memberRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(Member user, String jwtToken) {
        var token = Token.builder()
                .member(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Member user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Override
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.memberRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
    @Override
    public Member update(Member member) {
        if(memberRepository.findMemberByNum(member.getNum())!=null)
        {
            return memberRepository.save(member);
        }
        throw new NotFoundException();
    }

    @Override
    public Member updateRole(Role role,Integer num) {
        Member member=memberRepository.findMemberByNum(num);
        if(member!=null)
        {
            member.setRole(role);
            return memberRepository.save(member);
        }
        throw new NotFoundException();
    }


    @Override
    public Member approveAccount(Integer num) {
        Member member = memberRepository.findMemberByNum(num);
        if (member != null) {
            member.setAccountApproved(true);
           return  memberRepository.save(member);
        } else {
            throw new NotFoundException();
        }
    }
    @Override
    public Member findByNum(Integer num){
        return memberRepository.findMemberByNum(num);
    }
    @Override
    public void delete(Member member) {
         memberRepository.delete(member);
    }

    @Override
    public Optional<Member> findById(Member member) {
        return memberRepository.findById(member.getId());
    }

    @Override
    public List<Member> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Member> memberPage = memberRepository.findAll(pageable);
        return memberPage.getContent();
    }
    @Override
    public Page<Member> searchMember(String keySearch, Pageable page) {
        if(keySearch!=null) {
            if (keySearch.matches("\\d+"))
                return memberRepository.findByNumOrNameOrFamilyName(Integer.valueOf(keySearch), "", "",page);
            else
                return memberRepository.findByNumOrNameOrFamilyName(null, keySearch, keySearch,page);
        }
        return memberRepository.findAll(page);
    }
}
