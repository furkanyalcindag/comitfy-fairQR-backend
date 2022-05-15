package com.comitfy.healtie.app.service;

import com.comitfy.healtie.app.dto.AcademicInfoDTO;
import com.comitfy.healtie.app.dto.requestDTO.AcademicInfoRequestDTO;
import com.comitfy.healtie.app.entity.AcademicInfo;
import com.comitfy.healtie.app.entity.Doctor;
import com.comitfy.healtie.app.mapper.AcademicInfoMapper;
import com.comitfy.healtie.app.repository.AcademicInfoRepository;
import com.comitfy.healtie.app.repository.DoctorRepository;
import com.comitfy.healtie.util.PageDTO;
import com.comitfy.healtie.util.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AcademicInfoService extends BaseService<AcademicInfoDTO, AcademicInfoRequestDTO, AcademicInfo, AcademicInfoRepository, AcademicInfoMapper> {

    @Autowired
    AcademicInfoRepository academicInfoRepository;

    @Autowired
    AcademicInfoMapper academicInfoMapper;

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public AcademicInfoRepository getRepository() {
        return academicInfoRepository;
    }

    @Override
    public AcademicInfoMapper getMapper() {
        return academicInfoMapper;
    }

    public PageDTO<AcademicInfoDTO> getAcademicInfoByDoctor(UUID id, int page, int size) {
        Optional<Doctor> doctor = doctorRepository.findByUuid(id);
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        if (doctor.isPresent()) {
            return getMapper().pageEntityToPageDTO(academicInfoRepository.findAllByDoctor(pageable, doctor.get()));
        } else {
            return null;
        }
    }

    public AcademicInfoRequestDTO saveAcademicInfoByDoctor(UUID id, AcademicInfoRequestDTO dto) {
        Optional<Doctor> doctor = doctorRepository.findByUuid(id);
        if (doctor.isPresent()) {
            AcademicInfo academicInfo = getMapper().requestDTOToEntity(dto);
            academicInfo.setDoctor(doctor.get());
            academicInfoRepository.save(academicInfo);
            return dto;
        } else {
            return null;
        }
    }


}

   /* public RequestDTO update(UUID id, RequestDTO dto) {
        Optional<Entity> entity = getRepository().findByUuid(id);

        if (entity.isPresent()) {
            Entity entity1 = getMapper().requestDTOToExistEntity(entity.get(), dto);
            getRepository().save(entity1);
            return dto;
        } else {
            return null;
        }


    }*/


/*    List<AcademicInfo> academicInfoList = new ArrayList<>();
        academicInfoList.add(academicInfo);
        academicInfo.setDoctor(this);*/

/*    public RequestDTO save(UUID id, RequestDTO dto) {
        Optional<Entity> entity = getRepository().findByUuid(id);
        if (entity.isPresent()) {
            Entity entity1 = getMapper().requestDTOToEntity(dto);
            getRepository().save(entity1);
            return dto;
        } else {
            return null;
        }
    }*/

 /*  public ResponseEntity<AcademicInfoRequestDTO> add(AcademicInfoRequestDTO academicInfoRequestDTO) {
        getRepository().save(aca);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/
