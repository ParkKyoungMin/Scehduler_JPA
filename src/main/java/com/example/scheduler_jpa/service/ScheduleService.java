package com.example.scheduler_jpa.service;

import com.example.scheduler_jpa.entity.Schedule;
import com.example.scheduler_jpa.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Optional<Schedule> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    public Schedule updateSchedule(Long id, Schedule newSchedule) {
        return scheduleRepository.findById(id).map(schedule -> {
            schedule.setUsername(newSchedule.getUsername());
            schedule.setTitle(newSchedule.getTitle());
            schedule.setContent(newSchedule.getContent());
            return scheduleRepository.save(schedule);
        }).orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
