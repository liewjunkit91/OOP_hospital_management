package hospital.management;

/**
 * Interface for entities that can schedule and manage appointments.
 * This interface enables polymorphism by allowing different classes
 * (such as Doctor and Patient) to implement appointment scheduling
 * with their own specific behaviors.
 */
public interface Schedulable {
    /**
     * Schedules an appointment.
     *
     * @param appointment The appointment to schedule
     */
    void scheduleAppointment(Appointment appointment);

    /**
     * Cancels an appointment.
     *
     * @param appointment The appointment to cancel
     */
    void cancelAppointment(Appointment appointment);

    /**
     * Views the schedule of appointments.
     */
    void viewSchedule();
}

