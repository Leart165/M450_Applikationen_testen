package ch.tbz.m450.testing.tools.repository;

import ch.tbz.m450.testing.tools.repository.entities.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository.deleteAll();
    }

    @Test
    void shouldSaveAndFindStudent() {
        Student student = new Student("John Doe", "john.doe@tbz.ch");

        Student savedStudent = studentRepository.save(student);

        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getId()).isPositive();
        assertThat(savedStudent.getName()).isEqualTo("John Doe");
        assertThat(savedStudent.getEmail()).isEqualTo("john.doe@tbz.ch");
    }

    @Test
    void shouldFindAllStudents() {
        Student student1 = new Student("Alice Johnson", "alice.johnson@tbz.ch");
        Student student2 = new Student("Bob Wilson", "bob.wilson@tbz.ch");

        studentRepository.save(student1);
        studentRepository.save(student2);

        List<Student> students = (List<Student>) studentRepository.findAll();

        assertThat(students).hasSize(2);
        assertThat(students).extracting(Student::getName)
                .containsExactlyInAnyOrder("Alice Johnson", "Bob Wilson");
    }

    @Test
    void shouldReturnEmptyListWhenNoStudents() {
        List<Student> students = (List<Student>) studentRepository.findAll();

        assertThat(students).isEmpty();
    }

    @Test
    void shouldFindStudentById() {
        Student student = new Student("Jane Smith", "jane.smith@tbz.ch");
        Student savedStudent = studentRepository.save(student);

        Optional<Student> foundStudent = studentRepository.findById(savedStudent.getId());

        assertThat(foundStudent).isPresent();
        assertThat(foundStudent.get().getName()).isEqualTo("Jane Smith");
        assertThat(foundStudent.get().getEmail()).isEqualTo("jane.smith@tbz.ch");
    }

    @Test
    void shouldDeleteStudent() {
        Student student = new Student("Carol Brown", "carol.brown@tbz.ch");
        Student savedStudent = studentRepository.save(student);

        studentRepository.deleteById(savedStudent.getId());

        Optional<Student> deletedStudent = studentRepository.findById(savedStudent.getId());
        assertThat(deletedStudent).isEmpty();
    }

    @Test
    void shouldCountStudents() {
        studentRepository.save(new Student("Student 1", "student1@tbz.ch"));
        studentRepository.save(new Student("Student 2", "student2@tbz.ch"));
        studentRepository.save(new Student("Student 3", "student3@tbz.ch"));

        long count = studentRepository.count();

        assertThat(count).isEqualTo(3);
    }
}