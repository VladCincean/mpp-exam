package ro.droptable.exam.web.converter;

import ro.droptable.exam.core.model.BaseEntity;
import ro.droptable.exam.web.dto.BaseDto;

/**
 * Created by vlad on 19/06/2017.
 */
public interface Converter<
        Model extends BaseEntity<Long>,
        Dto extends BaseDto
        > {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);
}
