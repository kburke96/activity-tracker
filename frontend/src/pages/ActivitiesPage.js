import './ActivitiesPage.scss';
import { React, useEffect, useState } from 'react';
import { ActivitySmallCard } from '../components/ActivitySmallCard';
import { Navbar } from '../components/Navbar';
import Pagination from "@material-ui/lab/Pagination";

export const ActivitiesPage = ({ setToken }) => {
    const baseUrl = process.env.REACT_APP_BACKEND_IP;
    const activitiesUrl = "http://" + baseUrl + ":8080/activities";
    const [activity, setActivity] = useState([]);
    const [data, setData] = useState([]);
    const [url, setUrl] = useState(activitiesUrl);

    let headers = new Headers();
    headers.set('Authorization', 'Bearer ' + sessionStorage.getItem('token').replace(/"/g, ''));

    const fetchActivities = async () => {
        const response = await fetch(url, {
            method:'GET',
            headers: headers
        });
        const data = await response.json();

        console.log(data);
        setActivity(data.content);
        setData(data);
    };

    const handlePageChange = (e, value) => {
        const selectedPage = value - 1;
        setUrl(activitiesUrl + '?pageNo=' + selectedPage);
    };


    useEffect(
        () => {
            fetchActivities();
        }, [url]

    );



    return (
        <div className="ActivitiesPage">
            <h1>Activities</h1>
            <Navbar />
            <div className="pagination-container">
                <Pagination
                    className="pagination"
                    count={data.totalPages}
                    page={data.number + 1}
                    siblingCount={1}
                    boundaryCount={1}
                    variant="outlined"
                    shape="rounded"
                    onChange={handlePageChange}
                />
            </div>
            {activity.map(activity => <ActivitySmallCard activity={activity} />)}
        </div>
    );
}
