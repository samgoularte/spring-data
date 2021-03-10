package br.com.localhost;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import br.com.localhost.entity.Address;
import br.com.localhost.entity.Address.TypeAddress;
import br.com.localhost.entity.Document;
import br.com.localhost.entity.Person;
import br.com.localhost.entity.Phone;
import br.com.localhost.entity.Phone.TypePhone;
import br.com.localhost.entity.User;
import br.com.localhost.repository.AddressRepository;
import br.com.localhost.repository.DocumentRepository;
import br.com.localhost.repository.PersonRepository;
import br.com.localhost.repository.PhoneRepository;
import br.com.localhost.repository.UserRepository;

@SpringBootApplication
//@ImportResource(value = "spring-data.xml")
public class CursoSpringDataApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private PhoneRepository phoneRepository;
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		 testConfiguration();
		// testSave();
		// testUpdate();
		// testDelete();
		// testSavePersons();
		// testDeletePersons();
		// testFindAndSort();
		// testFindByIds();
		// testExists();
		// testPagination();

		// testByAge();
		// testByFirstNameLike();
		// testByAndOr();
		// testByBetween();
		// testByLastNameAndBetween();
		// testByGreaterAndLess();
		// testByGreaterAndLessEquals();
		// testByFirstNameGreaterThan();
		// testByStartAndEnd();
		// testByContaining();
		// testByAddressStartAndEnding();
		// testByInAndNotIn();
		// testByOrderBy();
		// testIgnoreCase();
		// testByNotNullAndNull();
		// testPhonesByNumber();
		// testFindByGreaterThanAndOrder();

		// findFisrtName();
		// findFirstNameOrAge();
		// findFirstNameAndAge();
		// findPersonByCPFEndsWith();
		// findPersonByAges();
		// findPersonByNames();
		// findDocumentByCPFStart();

		// findAddressesByCidade();

		// findAddressPorCidade();
		// findAddressesPorEndereco();

		// testFunctionAddress();

		// testProcedureCPF();

		// updatePhones();
		// deletePhone();

		//testeUser();
	}

	private void testeUser() {
		User u = new User();
		u.setUsername("sgoularte@gmail.com");
		u.setPassword("123mudar");
		
		if(u.isNew()) {
			userRepository.save(u);
		}		
	}
	
	private void deletePhone() {
		int result = phoneRepository.deleteByPhoneNumber("32220632");
		System.out.println("result = " + result);
	}

	private void updatePhones() {
		// int result = phoneRepository.setPhoneNumber("33339999", 1L);
		// System.out.println("result = " + result);

		int result = phoneRepository.setPhoneNumber(TypePhone.RESIDENCIAL, 1L);
		System.out.println("result = " + result);
	}

	private void testProcedureCPF() {
		String cpf1 = documentRepository.replaceCPF(2L);
		System.out.println("CPF: " + cpf1);

		String cpf2 = documentRepository.procedureReplaceCPF(5L);
		System.out.println("CPF: " + cpf2);
	}

	private void testFunctionAddress() {
		String ad1 = addressRepository.functionConcatenaEndereco(7L);
		System.out.println(ad1);

		String ad2 = addressRepository.functionNativeQueryConcatenaEndereco(2L);
		System.out.println(ad2);
	}

	private void findAddressesPorEndereco() {
		Address ad1 = addressRepository.buscaPorEndereco("Rio de Janeiro", "Av. Copacabana, 102");
		System.out.println(ad1.toString());

		Address ad2 = addressRepository.buscaPorCidadeRua("Rio de Janeiro", "Av. Ipanema, 36");
		System.out.println(ad2.toString());
	}

	private void findAddressPorCidade() {
		List<Address> addresses = addressRepository.buscaPorCidade("Porto Alegre");
		addresses.forEach(System.out::println);
	}

	private void findAddressesByCidade() {
		List<Address> addresses = addressRepository.buscaPorCidade("Rio de Janeiro");
		addresses.forEach(System.out::println);
	}

	private void findDocumentByCPFStart() {
		List<Document> documents = documentRepository.findByCPFStartWith("445");
		documents.forEach(System.out::println);
	}

	private void findPersonByNames() {
		List<Person> persons = personRepository.findByFirstNames("Aline", "Bruna", "Gilson", "Ana Maria");
		persons.forEach(System.out::println);
	}

	private void findPersonByAges() {
		List<Person> persons = personRepository.findByAgeBetween(28, 36);
		persons.forEach(System.out::println);
	}

	private void findPersonByCPFEndsWith() {
		List<Person> persons = personRepository.findByDocumentCPFEndsWith("98");
		persons.forEach(System.out::println);
	}

	private void findFirstNameAndAge() {
		List<Person> persons = personRepository.findByFirstNameAndAge(29, "Fabiana");
		persons.forEach(System.out::println);
	}

	private void findFirstNameOrAge() {
		List<Person> persons = personRepository.findByFirstNameOrAge("Aline", 29);
		persons.forEach(System.out::println);
	}

	private void findFisrtName() {
		List<Person> persons = personRepository.findByFirstName("Aline");
		persons.forEach(System.out::println);
	}

	private void testFindByGreaterThanAndOrder() {
		List<Person> p1 = personRepository.findByAgeGreaterThanOrderByFirstNameAscLastNameAsc(22);
		p1.forEach(System.out::println);
	}

	private void testPhonesByNumber() {
		List<Person> p1 = personRepository.findByPhonesNumberStartingWith("3222");
		p1.forEach(System.out::println);

	}

	private void testByNotNullAndNull() {
		List<Person> p1 = personRepository.findByDocumentIsNull();
		p1.forEach(System.out::println);

		System.out.println("--- --- --- --- --- ---");

		List<Person> p2 = personRepository.findByDocumentIsNotNull();
		p2.forEach(System.out::println);
	}

	private void testIgnoreCase() {
		List<Person> p1 = personRepository.findByFirstNameIgnoreCase("BRuNA");
		p1.forEach(System.out::println);
	}

	private void testByOrderBy() {
		List<Address> a1 = addressRepository.findByCityOrderByTypeDesc("Rio de Janeiro");
		a1.forEach(System.out::println);
	}

	private void testByInAndNotIn() {
		List<Person> p1 = personRepository.findByAgeIn(24, 28, 36, 45);
		p1.forEach(System.out::println);

		System.out.println("--- --- --- --- --- --- ");

		List<Person> p2 = personRepository.findByAgeNotIn(24, 28, 36, 45);
		p2.forEach(System.out::println);
	}

	private void testByAddressStartAndEnding() {
		List<Address> a1 = addressRepository.findByCityStartingWithOrStreetEndingWith("Rio", "102");
		a1.forEach(System.out::println);
	}

	private void testByContaining() {
		List<Address> a1 = addressRepository.findByStreetContaining("Ipanema");
		a1.forEach(System.out::println);
	}

	private void testByStartAndEnd() {
		List<Address> a1 = addressRepository.findByCityStartingWith("Rio");
		a1.forEach(System.out::println);

		System.out.println(" --- --- --- --- --- ---");

		List<Address> a2 = addressRepository.findByStreetEndingWith("102");
		a2.forEach(System.out::println);
	}

	private void testByFirstNameGreaterThan() {
		List<Person> p1 = personRepository.findByFirstNameGreaterThan("Am");
		p1.forEach(System.out::println);
	}

	private void testByGreaterAndLessEquals() {
		List<Person> p1 = personRepository.findByAgeGreaterThanEqual(28);
		p1.forEach(System.out::println);

		System.out.println(" --- --- --- --- --- ---");

		List<Person> p2 = personRepository.findByAgeLessThanEqual(28);
		p2.forEach(System.out::println);
	}

	private void testByGreaterAndLess() {
		List<Person> p1 = personRepository.findByAgeGreaterThan(28);
		p1.forEach(System.out::println);

		System.out.println(" --- --- --- --- --- ---");

		List<Person> p2 = personRepository.findByAgeLessThan(28);
		p2.forEach(System.out::println);
	}

	private void testByLastNameAndBetween() {
		List<Person> p1 = personRepository.findByLastNameAndAgeBetween("Figueira", 35, 36);
		p1.forEach(System.out::println);
	}

	private void testByBetween() {
		List<Person> p1 = personRepository.findByAgeBetween(24, 29);
		p1.forEach(System.out::println);
	}

	private void testByAndOr() {
		Person p1 = personRepository.findByFirstNameAndLastName("Aline", "Gomes");
		System.out.println(p1.toString());

		System.out.println(" --- --- --- --- --- ---");

		List<Person> p2 = personRepository.findByAgeOrFirstName(29, "Bruna");
		p2.forEach(System.out::println);
	}

	private void testByFirstNameLike() {
		List<Person> p1 = personRepository.findByFirstNameLike("Aline");
		p1.forEach(System.out::println);

		System.out.println(" --- --- --- --- --- ---");

		List<Person> p2 = personRepository.findByFirstNameNotLike("Aline");
		p2.forEach(System.out::println);
	}

	private void testByAge() {
		List<Person> p1 = personRepository.findByAgeIn(36);
		p1.forEach(System.out::println);

		System.out.println(" --- --- --- --- --- ---");

		List<Person> p2 = personRepository.findByAgeNot(36);
		p2.forEach(System.out::println);
	}

	/*
	 * private void testPagination() {
	 * 
	 * Page<Person> pages = personRepository.findAll(new PageRequest(0, 4));
	 * pages.getContent().forEach(System.out::println);
	 * 
	 * pages = personRepository.findAll(new PageRequest(1, 4));
	 * pages.getContent().forEach(System.out::println);
	 * 
	 * pages = personRepository.findAll(new PageRequest(2, 4));
	 * pages.getContent().forEach(System.out::println);
	 * 
	 * }
	 */
	private void testExists() {
		boolean p1 = personRepository.existsById(5L);
		System.out.println("P1 is " + p1);

		boolean p2 = personRepository.existsById(50L);
		System.out.println("P2 is " + p2);
	}

	private void testFindByIds() {
		List<Person> persons = personRepository.findAllById(Arrays.asList(1L, 5L, 7L, 11L));
		persons.forEach(System.out::println);
	}

	private void testFindAndSort() {
		Order orderAsc = new Order(Direction.ASC, "lastName");
		Order orderDesc = new Order(Direction.ASC, "firstName");

		List<Person> persons = personRepository.findByFirstNameAndSort("Samuel", Sort.by("lastName"));
		persons.forEach(System.out::println);
	}

	private void testDeletePersons() {
		Person p1 = personRepository.findById(35L).get();
		Person p2 = personRepository.findById(36L).get();
		Person p3 = personRepository.findById(37L).get();
		personRepository.deleteAll(Arrays.asList(p1, p2, p3));

		System.out.println("********************************************");

		Person p4 = personRepository.findById(38L).get();
		Person p5 = personRepository.findById(39L).get();
		Person p6 = personRepository.findById(40L).get();
		personRepository.deleteInBatch(Arrays.asList(p4, p5, p6));
	}

	private void testSavePersons() {
		Person p1 = new Person();
		p1.setFirstName("Alisson");
		p1.setLastName("Souza");
		p1.setAge(25);
		p1.setDocument(new Document("741.321.365-96", 963258852));

		Person p2 = new Person();
		p2.setFirstName("Bruno");
		p2.setLastName("Pereira");
		p2.setAge(63);
		p2.setDocument(new Document("741.369.212-96", 123654741));

		Person p3 = new Person();
		p3.setFirstName("Carlos Ricardo");
		p3.setLastName("Pereira");
		p3.setAge(45);
		p3.setDocument(new Document("701.309.212-96", 123054701));

		Person p4 = new Person();
		p4.setFirstName("Fabio");
		p4.setLastName("Guimarães");
		p4.setAge(33);
		p4.setDocument(new Document("742.329.212-96", 123624721));

		Person p5 = new Person();
		p5.setFirstName("Vinicius");
		p5.setLastName("Pereira");
		p5.setAge(23);
		p5.setDocument(new Document("141.319.212-96", 113614741));

		Person p6 = new Person();
		p6.setFirstName("Carla");
		p6.setLastName("Ribas");
		p6.setAge(23);
		p6.setDocument(new Document("541.315.212-96", 113655745));

		List<Person> persons = personRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));

		persons.forEach(System.out::println);
	}

	private void testDelete() {
		personRepository.deleteById(15L);
		Person person = personRepository.findById(14L).get();
		personRepository.delete(person);
		List<Person> persons = personRepository.findAll();
		persons.forEach(System.out::println);
	}

	private void testUpdate() {
		Person person = personRepository.findById(15L).get();
		System.out.println(person.toString());
		person.setLastName("Aguiar");

		personRepository.save(person);

		Person p2 = personRepository.findById(15L).get();
		System.out.println(p2.toString());
	}

	private void testSave() {
		Person person = new Person();
		person.setFirstName("João Luiz");
		person.setLastName("Rios");
		person.setAge(35);
		person.setDocument(new Document("841.852.963-74", 12365485));

		Address address = new Address();
		address.setCity("Manaus");
		address.setStreet("Rua das Valquirias, 32");
		address.setType(TypeAddress.RESIDENCIAL);

		person.setAddresses(Arrays.asList(address));
		person.addPhone(new Phone(TypePhone.RESIDENCIAL, "32220303"));

		personRepository.save(person);

		Person p2 = personRepository.findById(person.getId()).get();

		System.out.println(p2.toString());
	}

	private void testConfiguration() {
		long total = personRepository.count();
		System.out.println("Total of persons = " + total);

		List<Person> persons = personRepository.findAll();
		persons.forEach(System.out::println);

		long t2 = addressRepository.count();
		System.out.println("Total of addresses = " + t2);

		long t3 = documentRepository.count();
		System.out.println("Total of documents = " + t3);

		long t4 = phoneRepository.count();
		System.out.println("Total of phones = " + t4);
	}
}
