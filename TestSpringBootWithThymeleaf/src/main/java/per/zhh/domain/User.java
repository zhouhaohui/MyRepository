package per.zhh.domain;

public class User {

	private Integer id;
    private String name;
    private int age;
    private String address;
    
	public User(Integer id, String name, int age, String address) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.name=name;
		this.age=age;
		this.address=address;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
