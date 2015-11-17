package city;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import content.*;
import letter.*;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public class City
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private String name;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private List<Inhabitant> inhabs;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private int days;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private Set<Letter<?>> postBox;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public City(){
		this.inhabs = new ArrayList<>();
		this.postBox= new HashSet<>();
	}
	
	public boolean addInhabitant(Inhabitant inhab){
		return this.inhabs.add(inhab);
	}
	
	public boolean addInhabitants(List<Inhabitant> inhabs){
		return this.inhabs.addAll(inhabs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void distributeLetters() {
		for(Letter<?> letter : postBox){
			letter.toDo();
		}
		postBox.clear();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public boolean sendLetter(Letter<?> letter) {
		letter.toDo();
		return this.postBox.add(letter);	
	}
	
	public int getDays(){
		return this.days;
	}
	
	public void setDays(int days){
		this.days=days;
	}
	
	public Inhabitant getInhabitant(int id){
		return this.inhabs.get(id);
	}
	
	public static void main(String[] args){
		City city = new City();
		city.setDays(7);
		Random rand = new Random();
		for(int i=0;i<100;i++){
			city.addInhabitant(new Inhabitant("inhab" + i, new BankAccount(rand.nextInt(1000)))); 
		}
		for(int i=1;i<city.getDays();i++){
			System.out.println("***********************************");
			System.out.println("Day " + i);
			city.distributeLetters();
			int numberOfLetter = rand.nextInt(5)+1;
			for(int j=0;j<numberOfLetter;j++){
				int sender;
				int receiver;
				int type;
				sender = rand.nextInt(100);
				receiver = rand.nextInt(100);
				type = rand.nextInt(2);
				switch(type){
				case 0 :city.sendLetter(new SimpleLetter(city.getInhabitant(sender), city.getInhabitant(receiver), new Text(UUID.randomUUID().toString())));
						break;
				case 1 :city.sendLetter(new PromissoryNote(city.getInhabitant(sender), city.getInhabitant(receiver), new Money(rand.nextInt(100))));
						break;
				}
			}
		}
	}
}

