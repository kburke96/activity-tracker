import './SingleActivityPage.scss';
import { React, useEffect, useState } from 'react';
import { MapContainer, Polyline, TileLayer } from 'react-leaflet'
import { useParams } from 'react-router-dom';
import { ActivityDetailCard } from '../components/ActivityDetailCard';

export const SingleActivityPage = () => {

    const [activity, setActivity] = useState([]);
    const { activityId } = useParams();

    useEffect(
        () => {
            const fetchSingleActivity = async () => {
                console.log("Got activityId variable: " + activityId);
                const response = await fetch(`http://localhost:8080/activity?id=${activityId}`);
                const data = await response.json();
                setActivity(data);
            };

            fetchSingleActivity();
        }, [activityId]
    );


    return (
        <div className="SingleActivityPage">
            <h1>{activity.activityName}</h1>

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
            <ActivityDetailCard activity={activity}/>
        </div>
    );
}