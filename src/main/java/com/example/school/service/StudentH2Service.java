import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentH2Service {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public int createStudents(List<Student> students) {
        studentRepository.saveAll(students);
        return students.size();
    }

    public Student getStudentById(int studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Student updateStudent(int studentId, Student updatedStudent) {
        Student existingStudent = getStudentById(studentId);

        existingStudent.setStudentName(updatedStudent.getStudentName());
        existingStudent.setGender(updatedStudent.getGender());
        existingStudent.setStandard(updatedStudent.getStandard());

        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(int studentId) {
        Student student = getStudentById(studentId);
        studentRepository.delete(student);
    }
}

