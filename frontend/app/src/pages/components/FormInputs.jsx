import { Field, Input } from '@chakra-ui/react';

function TextInput({
  register,
  name,
  label,
  error,
  placeholder = '',
  ...rest
}) {
  return (
    <Field.Root invalid={!!error} {...rest}>
      <Field.Label>
        {label} <Field.RequiredIndicator />
      </Field.Label>
      <Input
        type="text"
        placeholder={placeholder}
        {...register(name)}
        borderColor="#CED4DA"
        borderRadius="md"
        p={4}
        _focus={{
          borderColor: '#007AFF',
          boxShadow: '0 0 0 2px rgba(0, 122, 255, 0.3)',
        }}
      />
      {error && (
        <Field.ErrorText color="#DC3545">{error.message}</Field.ErrorText>
      )}
    </Field.Root>
  );
}

function EmailInput({ register, name, error, ...rest }) {
  return (
    <Field.Root invalid={!!error} {...rest}>
      <Field.Label>
        Email <Field.RequiredIndicator />
      </Field.Label>
      <Input
        type="email"
        placeholder="Enter your email"
        {...register(name)}
        borderColor="#CED4DA"
        borderRadius="md"
        p={4}
        _focus={{
          borderColor: '#007AFF',
          boxShadow: '0 0 0 2px rgba(0, 122, 255, 0.3)',
        }}
      />
      {error && (
        <Field.ErrorText color="#DC3545">{error.message}</Field.ErrorText>
      )}
    </Field.Root>
  );
}

function PasswordInput({
  register,
  name,
  error,
  placeholder = 'Enter your password',
  ...rest
}) {
  return (
    <Field.Root invalid={!!error} {...rest}>
      <Field.Label>
        Password <Field.RequiredIndicator />
      </Field.Label>
      <Input
        type="password"
        placeholder={placeholder}
        {...register(name)}
        borderColor="#CED4DA"
        borderRadius="md"
        p={4}
        _focus={{
          borderColor: '#007AFF',
          boxShadow: '0 0 0 2px rgba(0, 122, 255, 0.3)',
        }}
      />
      {error && (
        <Field.ErrorText color="#DC3545">{error.message}</Field.ErrorText>
      )}
    </Field.Root>
  );
}

export { EmailInput, PasswordInput, TextInput };
