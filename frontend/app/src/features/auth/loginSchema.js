import { z } from 'zod';

const loginSchema = z.object({
  email: z.string().email('Email missing'),
  password: z.string().nonempty('Empty password'),
});

export { loginSchema };
