package web.com.spring.rest.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import web.com.spring.rest.entities.Person;
import web.com.spring.rest.exceptions.NoPersonFoundException;
import web.com.spring.rest.repository.PersonRepository;

import java.util.Optional;

@Service
public class PersonService {

    PersonRepository personRepository;

    PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Person createPerson(Person person) throws Exception {
        Optional<Person>  findIfPersonExists = personRepository.findByName(person.getName());

        if(findIfPersonExists.isPresent()){
           throw new NoPersonFoundException("Personi egziston");
        }
        return personRepository.save(person);
    }

    public Optional<Person> findByName(String name){
        return personRepository.findByName(name);
    }

    public Optional<Person> findById(long id){
        return personRepository.findById(id);
    }

    public void deletePerson(long id) throws Exception {
        Optional<Person>  findIfPersonExists = personRepository.findById(id);

        if(findIfPersonExists.isEmpty()) {
            throw new Exception("Personi nuk egziston");
        }
            personRepository.deleteById(id);
    }
}
