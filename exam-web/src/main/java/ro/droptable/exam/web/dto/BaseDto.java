package ro.droptable.exam.web.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Created by vlad on 19/06/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseDto implements Serializable {
    private Long id;
}
