import { RegisterForm } from '@src/features/registeruser/RegisterForm.jsx';

function RegisterPage() {
  return (
    <div
      style={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
      }}
    >
      <RegisterForm />
    </div>
  );
}

export default RegisterPage;
