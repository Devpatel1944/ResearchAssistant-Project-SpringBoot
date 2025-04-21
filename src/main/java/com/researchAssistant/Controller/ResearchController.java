package com.researchAssistant.Controller;

import com.researchAssistant.Entity.ResearchRequest;
import com.researchAssistant.Service.ResearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/research")
@CrossOrigin(origins = "*")
public class ResearchController {

    @Autowired
    private ResearchService researchService;

    @PostMapping("/process")
    public ResponseEntity<String> processContent( @RequestBody  ResearchRequest researchRequest){
        String result = researchService.processContent(researchRequest);
        return ResponseEntity.ok(result);
    }
}
