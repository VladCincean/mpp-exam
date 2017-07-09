package ro.droptable.exam.web.converter;

/**
 * Created by vlad on 19/06/2017.
 */
public interface ConverterGeneric<
        Model,
        Dto
        > {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);
}
