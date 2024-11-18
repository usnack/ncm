package io.usnack.ncm.whoedit.util.mapper;

import io.usnack.ncm.whoedit.domain.BlockEditLog;
import io.usnack.ncm.whoedit.service.dto.data.BlockEditLogDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlockEditLogMapper {
    BlockEditLogDto toDto(BlockEditLog blockEditLog);
}
