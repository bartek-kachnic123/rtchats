import { Field, Input } from '@chakra-ui/react';
import React from 'react';

const BaseFormField = React.forwardRef(function BaseFormField(props, ref) {
  const {
    label,
    children,
    helperText,
    errorText,
    optionalText,
    invalid,
    ...rest
  } = props;

  return (
    <Field.Root ref={ref} invalid={invalid} {...rest}>
      {label && (
        <Field.Label>
          {label}
          <Field.RequiredIndicator fallback={optionalText} />
        </Field.Label>
      )}
      {children}
      {helperText && <Field.HelperText>{helperText}</Field.HelperText>}
      {errorText && <Field.ErrorText>{errorText}</Field.ErrorText>}
    </Field.Root>
  );
});

function FormInput({
  register,
  name,
  label,
  error,
  placeholder = '',
  type = 'text',
  ...rest
}) {
  return (
    <BaseFormField
      label={label}
      invalid={!!error}
      errorText={error?.message}
      {...rest}
    >
      <Input
        type={type}
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
    </BaseFormField>
  );
}

export { BaseFormField, FormInput };
