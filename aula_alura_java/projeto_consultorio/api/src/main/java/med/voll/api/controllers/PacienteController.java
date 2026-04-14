package med.voll.api.controllers;

import med.voll.api.entities.Paciente;
import med.voll.api.services.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@Tag(name = "Pacientes", description = "Gerenciamento de pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService){this.pacienteService = pacienteService;}

    @GetMapping()
    public ResponseEntity<List<Paciente>> findAllPacientes(@RequestParam("page") int page, @RequestParam("size") int size){
        var pacientes = this.pacienteService.findAllPacientes(page, size);
        return ResponseEntity.ok(pacientes);
    }

}
