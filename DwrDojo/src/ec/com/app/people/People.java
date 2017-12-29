/*
 * Copyright 2005 Joe Walker
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

//import org.directwebremoting.datasync.StoreProvider;

import com.app.modelo.Contacto;
import com.app.service.impl.ServiceContact;

//import org.directwebremoting.datasync.StoreProvider;

import ec.com.app.model.Person;







/**
 * A container for 2 sets of people.
 * The smaller is designed for viewing all at the same time, and is viewable
 * and editable via {@link #getSmallCrowd()}, {@link #setPerson(Person)} and
 * {@link #deletePerson(String)}. The larger is accessible using the
 * {@link StoreProvider} registered under 'largeCrowd', and searchable using
 * {@link #getMatchingFromLargeCrowd(String)}
 * @author Joe Walker [joe at getahead dot ltd dot uk]
 */
public class People
{
    /**
     * Pre-populate the small and large crowds
     */
	ServiceContact serviceContact = new ServiceContact();

    /**
     * We maintain 2 lists of people, small (~10 people) and large (~1000).
     * The smaller is for when we want to show them all on the screen at the
     * same time, the larger for when we don'e.
     */
    public Collection<Person> getSmallCrowd()
    {
        return smallCrowd.values();
    }
    
    public List<Person> getAllPersons(String index){
    	List<Person> listPerson = new ArrayList<Person>();
    	List<Contacto> listContacto = new ArrayList<Contacto>();
    	listContacto = serviceContact.getInformationAllPersonsForOptions(index);
    	int ini = 0;
    	for (Contacto contacto : listContacto) {
    		 ini++;
			Person person = new Person(String.valueOf(ini), contacto.getPrimer_nombre(), "centro norte", 20, true);
			listPerson.add(person);
		}
    	return listPerson;
    }
    
    public List<Person> getPersons(){
    	List<Person> listPerson = new ArrayList<Person>();
    	Person person = new Person("1","Carlos", "norte", 20, true);
    	Person person2 = new Person("2","Pedro", "norte", 20, true);
    	listPerson.add(person);
    	listPerson.add(person2);
    	System.out.println("persons: "+listPerson.toString());
    	return listPerson;
    }

    /**
     * Insert a person into the set of people
     * @param person The person to add or update
     */
    public String setPerson(Person person)
    {
    	System.out.println("person :"+person.toString());
        smallCrowd.put(person.getId(), person);
        return "Updated values for " + person.getName();
    }

    /**
     * Delete a person from the set of people
     * @param id The id of the person to delete
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
     * Accessor for a subset of the current list of people
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
     * @see #getSmallCrowd()
     */
    private final Map<String, Person> smallCrowd = null;

    /**
     * @see #getMatchingFromLargeCrowd(String)
     */
    private final Map<String, Person> largeCrowd = null;

    /**
     * Both crowds are created in the same way.
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
