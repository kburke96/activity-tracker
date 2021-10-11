import './SingleActivityPage.scss';
import { React, useEffect, useState } from 'react';
import { MapContainer, Polyline, TileLayer } from 'react-leaflet'
import { useParams } from 'react-router-dom';
import { ActivityDetailCard } from '../components/ActivityDetailCard';
import { Navbar } from '../components/Navbar';


export const SingleActivityPage = () => {

    const [activity, setActivity] = useState([]);
    const { activityId } = useParams();

    let headers = new Headers();
    headers.set('Authorization', 'Bearer ' + sessionStorage.getItem('token').replace(/"/g, ''));

    useEffect(
        () => {
            const fetchSingleActivity = async () => {
                console.log("Got activityId variable: " + activityId);
                const response = await fetch(`http://localhost:8080/activities?id=${activityId}`, {
                    method:'GET',
                    headers: headers
                });
                const data = await response.json();
                setActivity(data);
            };

            fetchSingleActivity();
        }, [activityId]
    );


    return (
        <div className="SingleActivityPage">
            <h1>{activity.activityName}</h1>
            <Navbar />
            <MapContainer
                center={[52.7317535212683, -12.99685430908403]}
                zoom={7}
                scrollWheelZoom={false}
                className="map-container"
            >
                <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />
                <Polyline
                    pathOptions={{ fillColor: 'red', color: 'blue' }}
                    positions={[
                        [52.7317535212683, -12.99685430908403],
                        [52.73645655212683, -12.9365465908403],
                    ]}
                />
            </MapContainer>
            <ActivityDetailCard activity={activity} />
        </div>
    );
}