import { Text } from '@chakra-ui/react';
import { zodResolver } from '@hookform/resolvers/zod';
import { loginUser } from '@src/features/auth/authSlice.js';
import { loginSchema } from '@src/features/auth/loginSchema.js';
import { Form } from '@src/pages/components/Form.jsx';
import { InputField } from '@src/pages/components/InputField.jsx';
import { useForm } from 'react-hook-form';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router';

function LoginForm() {
  const {
    register,
    handleSubmit,
    formState: { errors: fieldErrors },
  } = useForm({
    resolver: zodResolver(loginSchema),
    mode: 'onSubmit',
    criteriaMode: 'all',
  });
  const { loading, error } = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const onSubmit = async (data) => {
    const resultAction = await dispatch(loginUser(data));
    if (loginUser.fulfilled.match(resultAction)) {
      navigate('/');
    }
  };

  return (
    <Form
      title="Login"
      onSubmit={handleSubmit(onSubmit)}
      submitLabel="Login"
      loading={loading}
    >
      <Text
        color="#DC3545"
        fontSize="sm"
        fontWeight="bold"
        textAlign="left"
        mt={1}
        minH="1.2em"
      >
        {error}
      </Text>
      <InputField
        name="email"
        type="email"
        label="Email"
        placeholder="Enter your email"
        register={register}
        error={fieldErrors.email}
        minH="90px"
        required
      />
      <InputField
        name="password"
        type="password"
        label="Password"
        placeholder="Enter your password"
        register={register}
        error={fieldErrors.password}
        minH="90px"
        required
      />
    </Form>
  );
}

export { LoginForm };
