package com.example.filRouge.controller;



import com.example.filRouge.controller.vm.inscription.requestInscription;
import com.example.filRouge.controller.vm.inscription.respondeInscription;
import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Inscription;
import com.example.filRouge.helpers.pdf.studentPdf;
import com.example.filRouge.service.choixService.ChoixService;
import com.example.filRouge.service.concourService.concourService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("FilRouge/api/choix")
@PreAuthorize("hasRole('MANAGER') OR hasRole('PROF')")
public class ChoixController {
    final private ChoixService choixService;

    final ModelMapper modelMapper;

    @GetMapping("/{reference}")
    public ResponseEntity<?> getAllChoixByConcour(@PathVariable("reference") String reference) {
        List<Inscription> inscriptions = choixService.findByConcours(Concour.builder().reference(reference).build());
        return new ResponseEntity<>(inscriptions.stream().map(f->modelMapper.map(f, respondeInscription.class)).toList(), HttpStatus.OK);
    }

    @GetMapping("/pdf/preselection/{reference}")
    public ResponseEntity<?> getAllChoixByConcourPreselectionPdf(@PathVariable("reference") String reference) throws Exception {
        List<Inscription> inscriptions = choixService.PeselectionListByConcours(Concour.builder().reference(reference).build());
        return generateTicket(inscriptions,"Preselection Student");
    }

    @GetMapping("/pdf/all/{reference}")
    public ResponseEntity<?> getAllChoixByConcourAllPdf(@PathVariable("reference") String reference) throws Exception {
        List<Inscription> inscriptions = choixService.findByConcours(Concour.builder().reference(reference).build());
        return generateTicket(inscriptions,"All Student");
    }

    @GetMapping("/pdf/writing/{reference}")
    public ResponseEntity<?> getAllChoixByConcourWritingPdf(@PathVariable("reference") String reference) throws Exception {
        List<Inscription> inscriptions = choixService.WritingListByConcours(Concour.builder().reference(reference).build());
        return generateTicket(inscriptions,"STUDENT Approved To Pass The Oral exam");
    }

    @GetMapping("/pdf/admis/{reference}")
    public ResponseEntity<?> getAllChoixByConcourAdmisPdf(@PathVariable("reference") String reference) throws Exception {
        List<Inscription> inscriptions = choixService.AdmisListByConcours(Concour.builder().reference(reference).build());
        return generateTicket(inscriptions,"STUDENT Accepted");
    }

    @GetMapping("/preselection/{reference}")
    public ResponseEntity<?> getAllChoixByConcourPreselection(@PathVariable("reference") String reference) {
        List<Inscription> inscriptions = choixService.PeselectionListByConcours(Concour.builder().reference(reference).build());
        return new ResponseEntity<>(inscriptions.stream().map(f->modelMapper.map(f, respondeInscription.class)).toList(), HttpStatus.OK);
    }

    @GetMapping("/writing/{reference}")
    public ResponseEntity<?> getAllChoixByConcourWriting(@PathVariable("reference") String reference) {
        List<Inscription> inscriptions = choixService.WritingListByConcours(Concour.builder().reference(reference).build());
        return new ResponseEntity<>(inscriptions.stream().map(f->modelMapper.map(f, respondeInscription.class)).toList(), HttpStatus.OK);
    }

    @GetMapping("/admis/{reference}")
    public ResponseEntity<?> getAllChoixByConcourAdmis(@PathVariable("reference") String reference){
        List<Inscription> inscriptions = choixService.AdmisListByConcours(Concour.builder().reference(reference).build());
        return new ResponseEntity<>(inscriptions.stream().map(f->modelMapper.map(f, respondeInscription.class)).toList(), HttpStatus.OK);
    }

    @GetMapping("/admisSeats/{reference}")
    public ResponseEntity<?> getAllChoixByConcourAdmisSeats(@PathVariable("reference") String reference){
        List<Inscription> inscriptions = choixService.FinalSeats(Concour.builder().reference(reference).build());
        return new ResponseEntity<>(inscriptions.stream().map(f->modelMapper.map(f, respondeInscription.class)).toList(), HttpStatus.OK);
    }

    @GetMapping("/preselectionSeats/{reference}")
    public ResponseEntity<?> getAllChoixByConcourPreselectionSeats(@PathVariable("reference") String reference){
        List<Inscription> inscriptions = choixService.PreselectionSeats(Concour.builder().reference(reference).build());
        return new ResponseEntity<>(inscriptions.stream().map(f->modelMapper.map(f, respondeInscription.class)).toList(), HttpStatus.OK);
    }
    @GetMapping("/oralSeats/{reference}")
    public ResponseEntity<?> getAllChoixByConcourOralSeats(@PathVariable("reference") String reference){
        List<Inscription> inscriptions = choixService.OralSeats(Concour.builder().reference(reference).build());
        return new ResponseEntity<>(inscriptions.stream().map(f->modelMapper.map(f, respondeInscription.class)).toList(), HttpStatus.OK);
    }

    public ResponseEntity<?> generateTicket(List<Inscription> inscriptions,String title) throws Exception {
        try {
            byte[] pdfBytes = studentPdf.generateTicketPdf(inscriptions,title);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
