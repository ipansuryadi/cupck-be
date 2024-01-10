context('POST /test', () => {
  it('should return 200 and receive message in body', () => {
    let messageSent: string = 'Cupcake Learning Program, Bisa! Bisa! Bisa!';
    cy.request({
      method: 'POST',
      url: '/test',
      body: { message: messageSent }
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body.messageResponse).to.eq('success')
      expect(response.body.messageReceived).to.eq(messageSent);
    });
  });
});