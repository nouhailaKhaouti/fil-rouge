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
@PreAuthorize("hasRole('MANAGER')")
public class ChoixController {
    final private concourService concourService;
    final private ChoixService choixService;

    final ModelMapper modelMapper;

    @GetMapping("/{reference}")
    public ResponseEntity<?> getAllChoixByConcour(@PathVariable("reference") String reference) {
        List<Inscription> inscriptions = choixService.findByConcours(Concour.builder().reference(reference).build());
        // Asynchronously generate the PDF
        return new ResponseEntity<>(inscriptions.stream().map(f->modelMapper.map(f, requestInscription.class)).toList(), HttpStatus.OK);
    }

    @GetMapping("/pdf/preselection/{reference}")
    public void getAllChoixByConcourPreselectionPdf(@PathVariable("reference") String reference,HttpServletResponse response) throws IOException {
        List<Inscription> inscriptions = choixService.PeselectionListByConcours(Concour.builder().reference(reference).build());
        generatePDF(response,inscriptions,"Preselection List for "+reference);
    }

    @GetMapping("/pdf/writing/{reference}")
    public void getAllChoixByConcourWritingPdf(@PathVariable("reference") String reference,HttpServletResponse response) throws IOException {
        List<Inscription> inscriptions = choixService.WritingListByConcours(Concour.builder().reference(reference).build());
        generatePDF(response,inscriptions,"Writing Exam List for "+reference);
    }

    @GetMapping("/pdf/admis/{reference}")
    public void getAllChoixByConcourAdmisPdf(@PathVariable("reference") String reference,HttpServletResponse response) throws IOException {
        List<Inscription> inscriptions = choixService.AdmisListByConcours(Concour.builder().reference(reference).build());
        generatePDF(response,inscriptions,"Final list for "+reference);
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

    public void generatePDF(HttpServletResponse response, List<Inscription> inscriptions,String title)throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=student_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        studentPdf exporter = new studentPdf(inscriptions);
        exporter.export(response,title);
    }

}
