export class SignInModel {
  constructor(
    public email: string,
    private accessToken: string,
    private role: string
  ) {}
  getRole(): string{
    return this.role;
  }
}
