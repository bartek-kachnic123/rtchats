import { Text, VStack } from '@chakra-ui/react';
import { zodResolver } from '@hookform/resolvers/zod';
import { registerSchema } from '@src/features/registeruser/registerSchema.js';
import { registerUser } from '@src/features/registeruser/registerSlice.js';
import { Form } from '@src/pages/components/Form.jsx';
import { InputField } from '@src/pages/components/InputField.jsx';
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
        name="username"
        type="text"
        label="Username"
        placeholder="Enter your username"
        register={register}
        error={fieldErrors.username}
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
      <InputField
        name="passwordConfirm"
        type="password"
        label="Password"
        placeholder="Confirm your password"
        register={register}
        error={fieldErrors.passwordConfirm}
        minH="90px"
        required
      />
    </Form>
  );
}

export { RegisterForm };
