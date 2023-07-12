package com.adobe.round2.controller;

import com.adobe.round2.exceptionhandler.TemplateNotFoundException;
import com.adobe.round2.exceptionhandler.ValidationFailedException;
import com.adobe.round2.service.MainService;
import com.adobe.round2.entity.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Controller
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    MainService serviceClass;

    /*
     * handle POST request for /resume
     */
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE , produces = {MediaType.APPLICATION_PDF_VALUE , MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> handleGet(@Valid @RequestBody Details details , BindingResult br) throws Exception {
        System.out.println(details);

        if(br.hasErrors()){
            StringBuilder builder = new StringBuilder();
            for(ObjectError s:br.getAllErrors()){
                builder.append("--").append(s.getDefaultMessage());
            }
            throw new ValidationFailedException( "Caused by :" + builder);
        }
        else if(!details.getTemplate_id().matches("[1-3]")){
            throw new TemplateNotFoundException("Invalid Template ID ( choose anything between 1-3 )");
        }

        return serviceClass.getPDFile(details);
    }

    /*
     * handle GET request for /resume/ui
     */
    @GetMapping("/ui")
    public String handleGet(Model model){
        Details details =  serviceClass.initializeDetails();
        model.addAttribute("details" , details);
        return "form";
    }


    /*
     * handle POST request for /resume/ui
     */
    @PostMapping(value = "/ui" )
    public ResponseEntity<Object> handlePost(@ModelAttribute Details details) throws Exception {
        System.out.println(details);
        Details newdetails =  serviceClass.filteredInput(details);
        System.out.println(newdetails);


        return serviceClass.getPDFile(newdetails);

    }



}
