package com.iespinosa.userFront.service.UserServiceImpl;

import com.iespinosa.userFront.dao.AppointmentDao;
import com.iespinosa.userFront.domain.Appointment;
import com.iespinosa.userFront.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentDao appointmentDao;

    public Appointment createAppointment(Appointment appointment) {
        return appointmentDao.save(appointment);
    }

    public List<Appointment> findAll() {
        return appointmentDao.findAll();
    }


    public Appointment findAppointment(Long id) {
        return appointmentDao.findAppointmentById(id);
    }

    public void confirmAppointment(Long id) {
        Appointment appointment = findAppointment(id);
        appointment.setConfirmed(true);
        appointmentDao.save(appointment);
    }
}
