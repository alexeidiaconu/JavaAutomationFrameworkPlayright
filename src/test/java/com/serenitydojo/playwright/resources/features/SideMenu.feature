Feature: Side Menu Flow

  Background: User logged in successfully
    Given Admin User is logged in with valid credentials
  @Smoke
  Scenario Outline: Verify Side Menu Actions
    Given the Side Menu is visible
    When a Side Menu "<option>" is clicked
    Then the corresponding "<page title>" title is displayed on the new opened page
    And the corresponding "<topbar title>" title is displayed on the Top Bar
    Examples:
      | option      | page title              | topbar title |
      | Admin       | System Users            | Admin        |
      | PIM         | Employee Information    | PIM          |
      | Leave       | Leave List              | Leave        |
      | Time        | Define Timesheet Period | Time         |
      | Recruitment | Candidates              | Recruitment  |
      | My Info     | Personal Details        | PIM          |
      | Performance | Performance             | Performance  |
      | Dashboard   |                         | Dashboard    |
      | Directory   | Directory               | Directory    |
      | Claim       | Employee Claims         | Claim        |
      | Buzz        | Buzz Newsfeed           | Buzz         |










