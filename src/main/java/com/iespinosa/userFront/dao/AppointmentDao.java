package com.iespinosa.userFront.dao;

import com.iespinosa.userFront.domain.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentDao extends CrudRepository<Appointment, Long> {

    List<Appointment> findAll();

    Appointment findAppointmentById(Long id);
}
