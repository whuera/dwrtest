/*
 * Copyright 2017 William Huera
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ec.com.app.people;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.modelo.Contacto;
import com.app.service.impl.ServiceContact;

import ec.com.app.model.Person;

public class People
{
	private static final Logger logger = LoggerFactory.getLogger(People.class);
    /** Pre-populate the small and large crowds. */
	ServiceContact serviceContact = new ServiceContact();

    /**
     * We maintain 2 lists of people, small (~10 people) and large (~1000).
     * The smaller is for when we want to show them all on the screen at the
     * same time, the larger for when we don'e.
     *
     * @return the small crowd
     */
    public Collection<Person> getSmallCrowd()
    {
        return smallCrowd.values();
    }
    
    /**
     * Gets the all persons.
     *
     * @param index the index
     * @return the all persons
     * @throws InterruptedException 
     */
    public List<Person> getAllPersons(String index, int sleepvalue) throws InterruptedException{    	
    	List<Contacto> listContacto = new ArrayList<Contacto>();
    	listContacto = serviceContact.getInformationAllPersonsForOptions(index);
    	int ini = 0;
    	List<Person> listPerson = new ArrayList<Person>();
    	for (Contacto contacto : listContacto) {
    		 ini++;
			Person person = new Person(String.valueOf(ini), contacto.getPrimer_nombre(), "centro norte", 20, true);
			Thread.sleep(sleepvalue);
			listPerson.add(person);
		}
    	logger.info("listperson: "+listPerson.toString());
    	return listPerson;
    }
    
    /**
     * Gets the persons.
     *
     * @return the persons
     */
    public List<Person> getPersons(){
    	List<Person> listPerson = new ArrayList<Person>();
    	Person person = new Person("1","Carlos", "norte", 20, true);
    	Person person2 = new Person("2","Pedro", "norte", 20, true);
    	listPerson.add(person);
    	listPerson.add(person2);
    	logger.info("persons: "+listPerson.toString());
    	return listPerson;
    }

    /**
     * Insert a person into the set of people.
     *
     * @param person The person to add or update
     * @return the string
     */
    public String setPerson(Person person)
    {
        smallCrowd.put(person.getId(), person);
        return "Updated values for " + person.getName();
    }

    /**
     * Delete a person from the set of people.
     *
     * @param id The id of the person to delete
     * @return the string
     */
    public String deletePerson(String id)
    {
        Person person = smallCrowd.remove(id);
        if (person == null)
        {
            return "Person does not exist";
        }
        else
        {
            return "Deleted " + person.getName();
        }
    }

    /**
     * Accessor for a subset of the current list of people.
     *
     * @param filter the filter
     * @return the current list of people
     */
    public List<Person> getMatchingFromLargeCrowd(String filter)
    {
        List<Person> reply = new ArrayList<Person>();
        Pattern regex = Pattern.compile(filter, Pattern.CASE_INSENSITIVE);
        for (Person person : largeCrowd.values())
        {
            if (regex.matcher(person.getName()).find())
            {
                reply.add(person);
            }
        }
        return reply;
    }

    /**
     * The small crowd.
     *
     * @see #getSmallCrowd()
     */
    private final Map<String, Person> smallCrowd = null;

    /**
     * The large crowd.
     *
     * @see #getMatchingFromLargeCrowd(String)
     */
    private final Map<String, Person> largeCrowd = null;

    /**
     * Both crowds are created in the same way.
     *
     * @param count the count
     * @return the map
     */
    private static Map<String, Person> createCrowd(int count)
    {
        Map<String, Person> reply = new HashMap<String, Person>();
        for (int i = 0; i < count; i++)
        {
            Person person = new Person("1", "William", "Centro", 30, true);
            reply.put(person.getId(), person);
        }
        return reply;
    }
}
