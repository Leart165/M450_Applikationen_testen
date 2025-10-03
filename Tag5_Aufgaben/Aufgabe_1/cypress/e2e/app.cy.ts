/// <reference types="cypress" />

describe('Studenten-Verwaltung', () => {
  beforeEach(() => {
    cy.visit('http://localhost:4200');
  });

  it('zeigt die Liste aller Studenten', () => {
    cy.get('[data-testid="studenten-tabelle"]').should('exist');
    cy.get('[data-testid="studenten-zeile"]').should('have.length.at.least', 1);
  });

  it('fügt einen neuen Studenten hinzu', () => {
    cy.get('[data-testid="input-name"]').type('Leart Mena');
    cy.get('[data-testid="input-alter"]').type('25');
    cy.get('[data-testid="button-speichern"]').click();
    cy.contains('Leart Mena').should('exist');
  });

  it('löscht einen Studenten', () => {
    cy.contains('Leart Mena').parent().find('[data-testid="button-loeschen"]').click();
    cy.contains('Leart Mena').should('not.exist');
  });
});
