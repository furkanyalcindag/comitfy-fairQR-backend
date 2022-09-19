package com.comitfy.healtie.util.common;

import com.comitfy.healtie.app.model.enums.LanguageEnum;
import com.comitfy.healtie.util.PageDTO;
import com.comitfy.healtie.util.dbUtil.BaseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.UUID;

public abstract class BaseWithMultiLanguageService<DTO extends BaseDTO, RequestDTO extends BaseDTO, Entity extends BaseEntity, Repository extends BaseWithMultiLanguageRepository<Entity>, Mapper extends BaseMapper<DTO, RequestDTO, Entity>, Specification extends BaseSpecification<Entity>> {


    public abstract Repository getRepository();

    public abstract Mapper getMapper();

    public abstract Specification getSpecification();


    public PageDTO<DTO> findAll(BaseFilterRequestDTO filterRequestDTO, LanguageEnum languageEnum) {
        Pageable pageable = PageRequest.of(filterRequestDTO.getPageNumber(), filterRequestDTO.getPageSize(), Sort.by("id"));


        if (filterRequestDTO.getLanguage() != null) {
            SearchCriteria sc = new SearchCriteria("languageEnum", "lang", "", LanguageEnum.valueOf(filterRequestDTO.getLanguage()));
            filterRequestDTO.getFilters().add(sc);
        } else {
            SearchCriteria sc = new SearchCriteria("languageEnum", "lang", "", languageEnum);
            filterRequestDTO.getFilters().add(sc);

        }


        getSpecification().setCriterias(filterRequestDTO.getFilters());
        //return getMapper().pageEntityToPageDTO(getRepository().findAllByLanguageEnum(pageable,languageEnum));
        return getMapper().pageEntityToPageDTO(getRepository().findAll(getSpecification(), pageable));

    }

    public PageDTO<DTO> findAll(int page, int size, LanguageEnum languageEnum) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));

        return getMapper().pageEntityToPageDTO(getRepository().findAllByLanguageEnum(pageable, languageEnum));
        // return getMapper().pageEntityToPageDTO( getRepository().findAll(getSpecification(),pageable))

    }


    public DTO findByUUID(UUID uuid) {
        Optional<Entity> optionalEntity = getRepository().findByUuid(uuid);
        return optionalEntity.map(entity -> getMapper().entityToDTO(entity)).orElse(null);
    }

    public Entity findEntityByUUID(UUID uuid) {
        Optional<Entity> optionalEntity = getRepository().findByUuid(uuid);
        return optionalEntity.orElse(null);
    }


    public DTO save(RequestDTO requestDTO) {
        Entity entity = getMapper().requestDTOToEntity(requestDTO);
        getRepository().save(entity);
        return getMapper().entityToDTO(entity);
    }

    void delete(UUID uuid) {
        Optional<Entity> optionalEntity = getRepository().findByUuid(uuid);
        if (optionalEntity.isPresent()) {
            Entity e = optionalEntity.get();
            e.setDeleted(true);
            /*Entity e = optionalEntity.get();
            e.setDeleted(true);
            getRepository().save(e);*/
            getRepository().save(e);
        }

    }

    public RequestDTO update(UUID id, RequestDTO dto) {
        Optional<Entity> entity = getRepository().findByUuid(id);

        if (entity.isPresent()) {
            Entity entity1 = getMapper().requestDTOToExistEntity(entity.get(), dto);
            getRepository().save(entity1);
            return dto;
        } else {
            return null;
        }


    }


}
