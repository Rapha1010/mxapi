package com.mxapi.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mxapi.modals.Terminal;
import com.mxapi.repositories.TerminalRepository;

@RestController
@RequestMapping("mxapi")
public class TerminalController {
	
	@Autowired
	private TerminalRepository tr;
	
	@GetMapping("listar")
	public ResponseEntity<Iterable<Terminal>> listar() {
		
		Iterable<Terminal> terminal  = tr.findAll();
		
		return ResponseEntity.ok(terminal);
	}
	
	@GetMapping("buscar/{version}/terminal/{logic}")
	public ResponseEntity<Terminal>  busca(@PathVariable("logic") long logic,@PathVariable("version") String version) {
		
		Terminal terminal = tr.findByAnyVariable(logic, version);
		
		if (terminal == null) return ResponseEntity.status(404).build();
		
		return ResponseEntity.ok(terminal);
	}
	
	@PostMapping("adicionar")
	public ResponseEntity<Terminal> adicionar(Terminal newTerminal,  @RequestBody String body) {
		
		String[] arrBody = body.split(";");
		
		//required ["logic", "serial", "model", "version"]
		if (arrBody[1].trim().isEmpty() || arrBody[0].trim().isEmpty()) return ResponseEntity.notFound().build();
		if (arrBody[2].trim().isEmpty() || arrBody[6].trim().isEmpty()) return ResponseEntity.notFound().build();

		newTerminal.setLogic(Integer.parseInt(arrBody[0]));
		newTerminal.setSerial(arrBody[1]);
		newTerminal.setModel(arrBody[2]);
		newTerminal.setSam(Integer.parseInt(arrBody[3]));
		newTerminal.setPtid(arrBody[4]);
		newTerminal.setPlat(Integer.parseInt(arrBody[5]));
		newTerminal.setVersion(arrBody[6]);
		newTerminal.setMxr(Integer.parseInt(arrBody[7]));
		newTerminal.setMxf(Integer.parseInt(arrBody[8]));
		newTerminal.setVerfm(arrBody[9]);
		
		Terminal terminal = tr.findByLogic(newTerminal.getLogic());
		
		if (terminal == null) return ResponseEntity.ok(tr.save(newTerminal));
		
		return ResponseEntity.ok(terminal);
	}
	
	@PutMapping("editar/{version}/terminal/{logic}")
	public ResponseEntity<Terminal> editContatoPost(@RequestBody Terminal newTerminal, @PathVariable("logic") long logic,@PathVariable("version") String version) {
		
		Terminal terminal = tr.findByAnyVariable(logic, version);
		
		if (terminal == null) return ResponseEntity.status(404).build();
		
		BeanUtils.copyProperties(newTerminal, terminal,"id");
		
		return ResponseEntity.ok(tr.save(terminal));
		
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteContato() {
		return ResponseEntity.notFound().build();
	}
	

}
