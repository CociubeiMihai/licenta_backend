package com.example.hospital.services.imp;

import com.example.hospital.dtos.PatientRequestDto;
import com.example.hospital.entities.RequestsPatient;
import com.example.hospital.entities.desease.Disease;
import com.example.hospital.repositories.AppUserRepository;
import com.example.hospital.repositories.RequestsPatientRepository;
import com.example.hospital.repositories.desease.DiseaseRepository;
import com.example.hospital.services.RequestsPatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RequestsPatientServiceImplementation implements RequestsPatientService {

    private final RequestsPatientRepository repository;
    private final DiseaseRepository diseaseRepository;
    private final AppUserRepository appUserRepository;

    public RequestsPatientServiceImplementation(RequestsPatientRepository repository, DiseaseRepository diseaseRepository, AppUserRepository appUserRepository) {
        this.repository = repository;
        this.diseaseRepository = diseaseRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void saveRequest(PatientRequestDto patientRequestDto) {
        RequestsPatient requestsPatient = RequestsPatient.builder()
                .contagios(patientRequestDto.isContagios())
                .febra(patientRequestDto.isFebra())
                .diagnostic(patientRequestDto.getDiagnostic())
                .reevaluare(patientRequestDto.isReevaluare())
                .data(patientRequestDto.getData())
                .appUser(appUserRepository.findById(patientRequestDto.getIdUser()).get())
                .sexFeminin(patientRequestDto.isSexFeminin())
                .build();
        if(patientRequestDto.getIdDisease() != null)
            requestsPatient.setDisease(diseaseRepository.findById(patientRequestDto.getIdDisease()).get());
        repository.save(requestsPatient);
    }

    @Override
    public RequestsPatient removeById(UUID id) {
        RequestsPatient requestsPatient = repository.findById(id).get();
        repository.delete(requestsPatient);
        return requestsPatient;
    }

    @Override
    public List<RequestsPatient> getAllRequests() {
        return repository.findAll();
    }
}
