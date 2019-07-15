package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.INTERNAL, "ProjectName");
        // when
        ProjectType projectType = project.getProjectType();
        // then
        Assertions.assertEquals(ProjectType.INTERNAL, projectType);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL, "Project A");
        // when
        ExpenseType typeAndName = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
        Assertions.assertEquals("EXPENSE_TYPE_A", typeAndName.toString());
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL, "Project B");
        // when
        ExpenseType typeAndName = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
        Assertions.assertEquals("EXPENSE_TYPE_B", typeAndName.toString());
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL, "Other Name");
        // when
        ExpenseType typeAndName = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
        Assertions.assertEquals("OTHER_EXPENSE", typeAndName.toString());
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        // given
        Project project = new Project(ProjectType.UNEXPECTED_PROJECT_TYPE, "UNEXPECTED PROJECT");
        // when
        UnexpectedProjectTypeException throwable = Assertions.assertThrows(UnexpectedProjectTypeException.class, ()->ExpenseService.getExpenseCodeByProjectTypeAndName(project));

        // then
        Assertions.assertThrows(UnexpectedProjectTypeException.class, ()->ExpenseService.getExpenseCodeByProjectTypeAndName(project));
        Assertions.assertEquals("You enter invalid project type", throwable.getMessage());
    }
}