package ec.com.app.model;

public class Person {
	
	public Person(String id, String name, String address, int age, boolean superhero) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.age = age;
		this.superhero = superhero;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String  id;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isSuperhero() {
		return superhero;
	}
	public void setSuperhero(boolean superhero) {
		this.superhero = superhero;
	}
	private String address;
	private int age;
	private boolean superhero;
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", address=" + address + ", age=" + age + ", superhero="
				+ superhero + "]";
	}
	
}
