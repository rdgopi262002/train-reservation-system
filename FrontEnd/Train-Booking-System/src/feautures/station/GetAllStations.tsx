import { useEffect, useState } from "react";
import type { StationResponse } from "../../types/station";
import axiosClient from "../../api/axiosClient";

export default function GetAllStations() {

    const [stations, setStations] = useState<StationResponse[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string>("");

    const [page, setPage] = useState<number>(0);
    const [size, setSize] = useState<number>(10);

    useEffect(() => {

        const getAllStations = async ():Promise<void> => {

            try {
                setLoading(true);

                const response = await axiosClient.get(
                    "/train/station/getAll",
                    {
                        params: {
                            page: page,
                            size: size
                        }
                    }
                );

                setStations(response.data.data.content);

            } catch (error) {

                setError("Failed to fetch stations");

            } finally {

                setLoading(false);
            }
        };

        getAllStations();

    }, [page, size]);


    if (loading) {
        return <p>Loading stations...</p>;
    }

    if (error) {
        return <p>{error}</p>;
    }

    return (
        <div>

            <h2>All Stations</h2>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Code</th>
                        <th>Station Name</th>
                        <th>State</th>
                        <th>Zone</th>
                    </tr>
                </thead>

                <tbody>
                    {stations.map((station) => (
                        <tr key={station.id}>
                            <td>{station.id}</td>
                            <td>{station.code}</td>
                            <td>{station.stationName}</td>
                            <td>{station.state}</td>
                            <td>{station.zone}</td>
                        </tr>
                    ))}
                </tbody>
            </table>

            <div>
                <button
                    onClick={() => setPage(page - 1)}
                    disabled={page === 0}
                >
                    Previous
                </button>

                <span> Page {page + 1} </span>

                <button
                    onClick={() => setPage(page + 1)}
                >
                    Next
                </button>
            </div>

        </div>
    );
}