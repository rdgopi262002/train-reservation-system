import { useState } from "react";
import { createStation } from "../../api/stationApi";
import "./CreateStation.css";
import axios from "axios";
import { createInitialStation } from "../../constants/station";
import type { StationResponse } from "../../types/station";
import StationForm from "./StationForm";
import ResponseStation from "./ResponseStation";


export default function CreateStation() {

  const [form, setForm] = useState(createInitialStation);

  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");
  const [station, setStation] = useState<StationResponse | null>(null);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.SyntheticEvent<HTMLFormElement>) => {
    e.preventDefault();
    setLoading(true);

    setMessage("");
    setStation(null); 

    try {
      const response=await createStation(form);
      setStation(response.data.data); 

      setMessage(response.data.message); 
      setForm(createInitialStation);

    } 
    
    catch (error) {
    if (axios.isAxiosError(error)) {
      setMessage(error.response?.data?.message || "Something went wrong");
    } else {
      setMessage("Unexpected error occurred");
    }
  } 
  
  finally {
      setLoading(false);
      
    }
  };

  return (
     <>
          {!station ? (
      <StationForm
        form={form}
        loading={loading}
        message={message}
        onChange={handleChange}
        onSubmit={handleSubmit}
      />
    ) : (
      <ResponseStation station={station} />
    )}
    </>
  );
}