package bg.softuni.proxies.cache;

import java.util.List;

public class StudentServiceImpl implements StudentServiceIfc {

  @Cacheable("students")
  @Override
  public List<Student> getAllStudents() {
    System.out.println("Doing some hard work to calculate the students.");
    Student student1 = new Student().setAge(23).setName("Pesho");
    Student student2 = new Student().setAge(27).setName("Anna");
    return List.of(student1, student2);
  }
}
