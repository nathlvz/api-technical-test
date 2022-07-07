package mx.com.technicaltest.model.movement;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The data retrived from the movements service
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class MovementDataWrapper {

	/**
	 * The movements list
	 */
	private List<MovementInfo> data;

	/**
	 * The data's size
	 */
	private long size;

}
