import { Text, VStack } from '@chakra-ui/react';
import { zodResolver } from '@hookform/resolvers/zod';
import { registerSchema } from '@src/features/registeruser/registerSchema.js';
import { registerUser } from '@src/features/registeruser/registerSlice.js';
import { Form } from '@src/pages/components/Form.jsx';
import {
  EmailInput,
  PasswordInput,
  TextInput,
} from '@src/pages/components/FormInputs.jsx';
import { useForm } from 'react-hook-form';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router';

function RegisterForm() {
  const dispatch = useDispatch();
  const { loading, errors: apiErrors } = useSelector((state) => state.register);
  const {
    register,
    handleSubmit,
    formState: { errors: fieldErrors },
  } = useForm({
    resolver: zodResolver(registerSchema),
    mode: 'onSubmit',
    criteriaMode: 'all',
  });
  const navigate = useNavigate();
  const onSubmit = async (data) => {
    const resultAction = await dispatch(registerUser(data));
    if (registerUser.fulfilled.match(resultAction)) {
      navigate('/login');
    }
  };

  const errorMap = apiErrors.reduce((acc, curr) => {
    const { field, defaultMessage } = curr;
    if (!acc[field]) acc[field] = [];
    acc[field].push(defaultMessage);
    return acc;
  }, {});

  return (
    <Form
      title="Register"
      onSubmit={handleSubmit(onSubmit)}
      submitLabel="Register"
      loading={loading}
    >
      {Object.keys(errorMap).map((field) => (
        <VStack key={field} spacing={1} minH="1em" w="100%">
          {errorMap[field].map((msg, index) => (
            <Text
              key={index}
              color="#DC3545"
              fontWeight="bold"
              textAlign="center"
            >
              {msg}
            </Text>
          ))}
        </VStack>
      ))}
      <EmailInput
        register={register}
        name="email"
        error={fieldErrors.email}
        minH="90px"
        required
      />
      <TextInput
        register={register}
        name="username"
        label="Username"
        error={fieldErrors.username}
        placeholder="Enter your username"
        minH="90px"
        required
      />
      <PasswordInput
        register={register}
        name="password"
        error={fieldErrors.password}
        minH="90px"
        required
      />
      <PasswordInput
        register={register}
        name="passwordConfirm"
        error={fieldErrors.passwordConfirm}
        placeholder="Confirm password"
        minH="90px"
        required
      />
    </Form>
  );
}

export { RegisterForm };
