package mx.com.technicaltest.mappers;

import static org.mapstruct.factory.Mappers.getMapper;

import org.mapstruct.Mapper;

import mx.com.technicaltest.entity.Movement;
import mx.com.technicaltest.model.movement.MovementInfo;

/**
 * Mapper interface for Movements
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
@Mapper
public interface MovementMapper {

	/**
	 * The instance retrived for this mapper.
	 */
	MovementMapper INSTANCE = getMapper(MovementMapper.class);

	/**
	 * Transform a {@code mx.com.technicaltest.model.movement.MovementInfo} to
	 * {@code mx.com.technicaltest.entity.Movement}.
	 * 
	 * @param movementInfo the source.
	 * @return a new Movement Object (the target).
	 */
	Movement mapToMovement(MovementInfo movementInfo);

}
