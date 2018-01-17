package improve.annotation2;

public class PersonDao {

	@InjectPerson(name="老王",age=23)
	private Person person;
	
	public Person getPerson(){
		return person;
	}
	
	@InjectPerson(name="老王",age=23)
	public void setPerson(Person person) {
		this.person = person;
	}
}
