import { Button } from "@mui/material";
import { TextField } from "@mui/material";
import { ErrorMessage, Field, Form, Formik, validateYupSchema } from "formik";
import React, { useState } from "react";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import * as Yup from "yup";

const initialValues = {
	firstName: "",
	lastName: "",
	gender: "",
	email: "",
	password: "",
};
const validationSchema = {
	email: Yup.string().email("Invalid Email").required("Email is Required"),
	password: Yup.string()
		.min(6, "Password must be more than 6 letters")
		.required("Password is required"),
};
const Register = () => {
	const [gender, setGender] = useState("");

	const handleSubmit = (values) => {
		values.gender = gender;
		console.log("handle submit", values);
	};

	const handleChange = (event) => {
		setGender(event.target.value);
	};

	return (
		<>
			<Formik
				initialValues={initialValues}
				onSubmit={handleSubmit}
				// validationSchema={validationSchema}
			>
				<Form className="space-y-5">
					<div className="space-y-5">
						<div>
							<Field
								as={TextField}
								name="firstName"
								placeholder="First Name"
								type="text"
								variant="outlined"
								fullWidth
							/>
							<ErrorMessage
								name="firstName"
								component={"div"}
								className="text-red-500"
							/>
						</div>
						<div>
							<Field
								as={TextField}
								name="lastNamme"
								placeholder="Last Name"
								type="text"
								variant="outlined"
								fullWidth
							/>
							<ErrorMessage
								name="lastName"
								component={"div"}
								className="text-red-500"
							/>
						</div>
						<div>
							<Field
								as={TextField}
								name="email"
								placeholder="Email"
								type="email"
								variant="outlined"
								fullWidth
							/>
							<ErrorMessage
								name="email"
								component={"div"}
								className="text-red-500"
							/>
						</div>
						<div>
							<Field
								as={TextField}
								name="password"
								placeholder="Password"
								type="password"
								variant="outlined"
								fullWidth
							/>
							<ErrorMessage
								name="password"
								component={"div"}
								className="text-red-500"
							/>
						</div>
						<div>
							<RadioGroup
								row
								aria-labelledby="gender"
								name="gender"
								onChange={handleChange}
							>
								<FormControlLabel
									value="female"
									control={<Radio />}
									label="Female"
								/>
								<FormControlLabel
									value="male"
									control={<Radio />}
									label="Male"
								/>
								<ErrorMessage
									name="gender"
									component={"div"}
									className="text-red-500"
								/>
							</RadioGroup>
						</div>
					</div>
					<Button
						sx={{ padding: ".8rem 0 rem" }}
						fullWidth
						type="submit"
						variant="contained"
						color="primary"
					>
						Register
					</Button>
				</Form>
			</Formik>
		</>
	);
};

export default Register;
