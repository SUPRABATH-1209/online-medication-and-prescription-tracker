package com.health.medicare.service.implementation;

import com.health.medicare.dto.request.PrescriptionRequestDto;
import com.health.medicare.dto.request.PrescriptionMedicineRequestDto;
import com.health.medicare.dto.response.PrescriptionMedicineResponseDto;
import com.health.medicare.dto.response.PrescriptionResponseDto;
import com.health.medicare.exception.ResourceNotFoundException;
import com.health.medicare.model.*;
import com.health.medicare.repository.*;
import com.health.medicare.service.PrescriptionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final MedicineRepository medicineRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;

    public PrescriptionServiceImpl(DoctorRepository doctorRepository,
                                   PatientRepository patientRepository,
                                   PrescriptionRepository prescriptionRepository,
                                   MedicineRepository medicineRepository,
                                   PrescriptionMedicineRepository prescriptionMedicineRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.medicineRepository = medicineRepository;
        this.prescriptionMedicineRepository = prescriptionMedicineRepository;
    }

    @Override
    public PrescriptionResponseDto createPrescription(PrescriptionRequestDto dto) {

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        Prescription prescription = new Prescription();
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        prescription.setPrescriptionDate(LocalDate.now());
        prescription.setRemarks(dto.getRemarks());

        Prescription savedPrescription = prescriptionRepository.save(prescription);

        List<PrescriptionMedicineResponseDto> medicineResponses =
                dto.getMedicines().stream().map(medDto -> {

                    Medicine medicine = medicineRepository.findById(medDto.getMedicineId())
                            .orElseThrow(() -> new ResourceNotFoundException("Medicine not found"));

                    PrescriptionMedicine pm = new PrescriptionMedicine();
                    pm.setPrescription(savedPrescription);
                    pm.setMedicine(medicine);
                    pm.setDosage(medDto.getDosage());
                    pm.setFrequency(medDto.getFrequency());
                    pm.setDurationDays(medDto.getDurationDays());

                    prescriptionMedicineRepository.save(pm);

                    return new PrescriptionMedicineResponseDto(
                            medicine.getMedicineName(),
                            pm.getDosage(),
                            pm.getFrequency(),
                            pm.getDurationDays()
                    );
                }).collect(Collectors.toList());

        return new PrescriptionResponseDto(
                savedPrescription.getPrescriptionId(),
                savedPrescription.getPrescriptionDate(),
                doctor.getName(),
                patient.getName(),
                medicineResponses
        );
    }

    @Override
    public List<PrescriptionResponseDto> getPrescriptionsByPatient(Long patientId) {

        return prescriptionRepository.findByPatientPatientId(  patientId)

                .stream()
                .map(p -> new PrescriptionResponseDto(
                        p.getPrescriptionId(),
                        p.getPrescriptionDate(),
                        p.getDoctor().getName(),
                        p.getPatient().getName(),
                        p.getPrescriptionMedicines()
                                .stream()
                                .map(pm -> new PrescriptionMedicineResponseDto(
                                        pm.getMedicine().getMedicineName(),
                                        pm.getDosage(),
                                        pm.getFrequency(),
                                        pm.getDurationDays()
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public PrescriptionResponseDto getPrescriptionById(Long id) {
        return null;
    }
}
