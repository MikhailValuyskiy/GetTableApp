package ru.example.mvaluyskiy.gettableapp.data.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public abstract class BaseMapper<VO, DTO> {

    abstract VO getFromDto(DTO dto);

    abstract DTO getFromVo(VO vo);

    public Collection<VO> getFromDtoCollection(Collection<DTO> dtoCollection) {
        List<VO> convertedVoList = new ArrayList<>();
        for (DTO dto : dtoCollection) {
            convertedVoList.add(getFromDto(dto));
        }

        return convertedVoList;
    }

    public Collection<DTO> getFromVoCollection(Collection<VO> voCollection) {
        List<DTO> convertedDtoList = new ArrayList<>();
        for (VO vo : voCollection) {
            convertedDtoList.add(getFromVo(vo));
        }

        return convertedDtoList;
    }
}
