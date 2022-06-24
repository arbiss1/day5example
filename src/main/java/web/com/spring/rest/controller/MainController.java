package web.com.spring.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.com.spring.rest.entities.Person;
import web.com.spring.rest.service.PersonService;

@RestController
@RequestMapping(value = "/api")
public class MainController {

    PersonService personService;

    MainController(PersonService personService){
        this.personService = personService;
    }

    private static final Logger log =   LoggerFactory.getLogger(MainController.class);

   //https:/localhost:8080/api/createPerson , HTTP -> Post (Dispacher Servlet)

//    @GetMapping - > read
//    @DeleteMapping -> delete
//    @PutMapping -> edit

//    @RequestMapping(method = RequestMethod.POST)

    //@PathVariable -> merr te dhena nga mappimi ne url



    @PostMapping("/create/person")
    public ResponseEntity<Person> createPerson(
            @RequestBody Person person
            ) throws Exception {
            log.info(person.getName());
            personService.createPerson(person);
            return new ResponseEntity(person, HttpStatus.OK);
    }


    //    https:/localhost:8080/api/createPerson/1

    @DeleteMapping("/delete/person/{id}")
    public ResponseEntity<String> deletePerson(
            @PathVariable(name = "id") long id
    ) throws Exception {
            personService.deletePerson(id);
            return new ResponseEntity("Personi me kete id u fshi", HttpStatus.OK);
    }

    @PutMapping("/edit/person")
    public ResponseEntity<Person> editPerson(
            @RequestBody Person person,
            @RequestParam long id
    ) throws Exception {
            person.setId(id);
            return new ResponseEntity(personService.createPerson(person), HttpStatus.OK);
        }

}
