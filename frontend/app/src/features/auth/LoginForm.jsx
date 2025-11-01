import {
  Box,
  Button,
  Field,
  Heading,
  Input,
  Text,
  VStack,
} from '@chakra-ui/react';
import { zodResolver } from '@hookform/resolvers/zod';
import { loginUser } from '@src/features/auth/authSlice.js';
import { loginSchema } from '@src/features/auth/loginSchema.js';
import { Form } from '@src/pages/components/Form.jsx';
import {
  EmailInput,
  PasswordInput,
} from '@src/pages/components/FormInputs.jsx';
import { useForm } from 'react-hook-form';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router';

function LoginForm() {
  const {
    register,
    handleSubmit,
    formState: { errors },
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
      <EmailInput
        register={register}
        name="email"
        error={errors.email}
        minH="90px"
        required
      />
      <PasswordInput
        register={register}
        name="password"
        error={errors.password}
        minH="90px"
        required
      />
    </Form>
  );
}

export { LoginForm };
