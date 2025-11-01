import { LoginForm } from '@src/features/auth/LoginForm.jsx';

function LoginPage() {
  return (
    <div
      style={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
      }}
    >
      <LoginForm />
    </div>
  );
}

export default LoginPage;
