package letter;

import content.Text;
import exception.NullOrNegativCostException;

/**
 * <!-- begin-user-doc -->
 * RegisteredLetter class
 * <!--  end-user-doc  -->
 * @generated
 */

public class RegisteredLetter extends DecoratorLetter
{
	/**
	 * <!-- begin-user-doc -->
	 * Builder
	 * @param letter letter that will be registered
	 * <!--  end-user-doc  -->
	 * @throws NullOrNegativCostException 
	 * @generated
	 */
	public RegisteredLetter(Letter<?> letter) throws NullOrNegativCostException{
		super(letter);
		super.letter.addCost(15);
		super.letter.typeLetter="a registered letter whose content is " + letter.typeLetter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * Do the action of the letter and prints the details
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void toDo() {
		if(super.letter.inBox){
			AoR aor = new AoR(super.letter.sender, super.letter.receiver, super.letter.content);
			String str = "acknowledgement of receipt for " + super.letter.typeLetter + "whose content is " + super.letter.typeContent + "(" + super.letter.content.toString() + ")";
			aor.content = new Text(str);
			super.letter.receiver.getCity().sendAoR(aor);
		}else{
			super.letter.toDo();
		}
	}
}

