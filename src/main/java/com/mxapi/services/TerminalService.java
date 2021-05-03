package com.mxapi.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
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
public class TerminalService {
	
	@Autowired
	private TerminalRepository tr;
	
	@GetMapping("listar")
	public ResponseEntity<Iterable<Terminal>> listar() {
		
		Iterable<Terminal> terminal  = tr.findAll();
		
		return ResponseEntity.ok(terminal);
	}
	
	@GetMapping("buscar/{version}/terminal/{logic}")
	public ResponseEntity<Iterable<Terminal>> buscar(@PathVariable("logic") long logic, @PathVariable("version") String version) {
		
		if (version.trim().isEmpty()) throw new IllegalArgumentException("Parms can't be null");
		if (logic <= 0) throw new IllegalArgumentException("Parms can't be <= 0");
		
		Iterable<Terminal> terminal;
		
		try {
			terminal = tr.findAllByAnyVariablet(logic, version.trim());
			
			if (terminal == null) throw new IllegalArgumentException("Instance doesn't exist");
			
			return ResponseEntity.ok(terminal);
			
		} catch(IllegalArgumentException e ) {
			return ResponseEntity.status(204).build();
		}
	}
	
	@PostMapping("adicionar")
	public ResponseEntity<Terminal> adicionar(Terminal newTerminal,  @RequestBody String body) {
		
		if (body.trim().isEmpty()) throw new IllegalArgumentException("Body isn't be empty");
		
		String[] arrBody = body.split(";");
		
		if (arrBody.length < 10) throw new IllegalArgumentException("Body needs to have 10 elements");
		
		if (arrBody[1].trim().isEmpty() || arrBody[0].trim().isEmpty()) throw new IllegalArgumentException("Required field can't be empty");
		if (arrBody[2].trim().isEmpty() || arrBody[6].trim().isEmpty()) throw new IllegalArgumentException("Required field can't be empty");
		
		try {
			
			newTerminal.setLogic(Integer.parseInt(arrBody[0].trim()));
			newTerminal.setSerial(arrBody[1]);
			newTerminal.setModel(arrBody[2]);
			newTerminal.setVersion(arrBody[6]);
			
			if (!arrBody[3].trim().isEmpty()) { newTerminal.setSam(Integer.parseInt(arrBody[3].trim()));}
			if (!arrBody[4].trim().isEmpty()) { newTerminal.setPtid(arrBody[4]); }
			if (!arrBody[5].trim().isEmpty()) { newTerminal.setPlat(Integer.parseInt(arrBody[5].trim())); }
			if (!arrBody[7].trim().isEmpty()) { newTerminal.setMxr(Integer.parseInt(arrBody[7].trim())); }
			if (!arrBody[8].trim().isEmpty()) { newTerminal.setMxf(Integer.parseInt(arrBody[8].trim())); }
			if (!arrBody[9].trim().isEmpty()) { newTerminal.setVerfm(arrBody[9]); }
			
			Terminal terminal = tr.findByAnyVariable(newTerminal.getLogic(), newTerminal.getVersion());
			
			if (terminal == null) return ResponseEntity.ok(tr.save(newTerminal));
			
			return ResponseEntity.ok(terminal);
		
		} catch(IllegalArgumentException e ) {
			return ResponseEntity.status(204).build();
		}
	}
	
	@PutMapping("editar/{version}/terminal/{logic}")
	public ResponseEntity<Terminal> editar(@RequestBody Terminal newTerminal, @PathVariable("logic") long logicPath,@PathVariable("version") String versionPath) {
		
		if (versionPath.trim().isEmpty()) throw new IllegalArgumentException("Parms can't be null");
		if (logicPath <= 0) throw new IllegalArgumentException("Parms can't be <= 0");
		
		long logic = newTerminal.getLogic();
		String version = newTerminal.getVersion();
		
		if(logic != logicPath && version != versionPath)  throw new IllegalArgumentException("Logic and Version need to be igual in path and body");
		
		try {
			Terminal terminal = tr.findByAnyVariable(logic, version);
			
			if (terminal == null) throw new IllegalArgumentException("Instance doesn't exist");
			
			BeanUtils.copyProperties(newTerminal, terminal,"id");
			return ResponseEntity.ok(tr.save(terminal));
		
		} catch(IllegalArgumentException e ) {
			return ResponseEntity.status(204).build();
		}
		
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deletar() {
		return ResponseEntity.status(404).build();
	}
	

}
